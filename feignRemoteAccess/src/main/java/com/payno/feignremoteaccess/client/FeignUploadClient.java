package com.payno.feignremoteaccess.client;

import feign.Client;
import feign.Feign;
import feign.RequestLine;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentProcessor;
import feign.form.ContentType;
import feign.form.MultipartFormContentProcessor;
import feign.form.UrlencodedFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author payno
 * @date 2019/11/17 15:52
 * @description
 */
@Service
public class FeignUploadClient {
    public interface FileUploadResource {
        //@RequestMapping(value = "/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        @RequestLine("POST /upload")
        String fileUpload(MultipartFile file);
    }
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    private static final String HTTP_FILE_UPLOAD_URL="http://TASK";

    @Autowired
    Client client;
    public static class MultiPartEncoder implements Encoder {
        private static final String CONTENT_TYPE_HEADER = "Content-Type";
        private static final Pattern CHARSET_PATTERN = Pattern.compile("(?<=charset=)([\\w\\-]+)");
        private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
        private final Encoder delegate;
        private final Map<ContentType, ContentProcessor> processors;

        public MultiPartEncoder() {
            this(new Default());
        }

        public MultiPartEncoder(Encoder delegate) {
            this.delegate = delegate;
            List<ContentProcessor> list = Arrays.asList(new MultipartFormContentProcessor(delegate), new UrlencodedFormContentProcessor());
            this.processors = new HashMap(list.size(), 1.0F);
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                ContentProcessor processor = (ContentProcessor)var3.next();
                this.processors.put(processor.getSupportedContentType(), processor);
            }

            MultipartFormContentProcessor processor = (MultipartFormContentProcessor)this.getContentProcessor(ContentType.MULTIPART);
            processor.addWriter(new SpringSingleMultipartFileWriter());
            processor.addWriter(new SpringManyMultipartFilesWriter());
        }

        public void encode0(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
            if (MAP_STRING_WILDCARD.equals(bodyType)) {
                Map data = (Map)object;
                try {
                    ((ContentProcessor)this.processors.get(ContentType.MULTIPART)).process(template, StandardCharsets.UTF_8, data);
                } catch (Exception var9) {
                    throw new EncodeException(var9.getMessage());
                }
            } else {
                this.delegate.encode(object, bodyType, template);
            }
        }

        public final ContentProcessor getContentProcessor(ContentType type) {
            return (ContentProcessor)this.processors.get(type);
        }

        private String getContentTypeValue(Map<String, Collection<String>> headers) {
            Iterator var2 = headers.entrySet().iterator();

            while(true) {
                Map.Entry entry;
                do {
                    if (!var2.hasNext()) {
                        return null;
                    }

                    entry = (Map.Entry)var2.next();
                } while(!((String)entry.getKey()).equalsIgnoreCase(CONTENT_TYPE_HEADER));

                Iterator var4 = ((Collection)entry.getValue()).iterator();

                while(var4.hasNext()) {
                    String contentTypeValue = (String)var4.next();
                    if (contentTypeValue != null) {
                        return contentTypeValue;
                    }
                }
            }
        }

        private Charset getCharset(String contentTypeValue) {
            Matcher matcher = CHARSET_PATTERN.matcher(contentTypeValue);
            return matcher.find() ? Charset.forName(matcher.group(1)) : DEFAULT_CHARSET;
        }
        @Override
        public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
            if(bodyType.equals(MultipartFile.class)){
                MultipartFile multipartFile=(MultipartFile)object;
                Map<String,Object> data=Collections.singletonMap(multipartFile.getName(),object);
                encode0(data, MAP_STRING_WILDCARD, template);
            }else if(bodyType.equals(Map.class)){
                Map<String, Object> data = (Map<String, Object>)object;
                encode0(data, MAP_STRING_WILDCARD, template);
            }else {
                encode0(object, bodyType, template);
            }
        }
    }
    public void uploadFile(MultipartFile file){
        Feign.builder().client(client).encoder(new MultiPartEncoder())
                //这里没有添加decoder了
                .target(FileUploadResource.class, HTTP_FILE_UPLOAD_URL)
                .fileUpload(file);
    }
}
