package Utils;



import com.wenge.tbase.wos.WOSClient;
import com.wenge.tbase.wos.errors.*;
import com.wenge.tbase.wos.messages.Part;

import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;


import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



@Slf4j
@Data

public class MinioUtil {

/*
* # minio
minio.ip=http://192.168.4.14
minio.port=8091
minio.access-key=minioadmin
minio.secret-key=minioadmin
minio.address=http://192.168.4.14:8091
minio.https.address=http://192.168.4.14:8091
minio.default-name=bucket-traceability*/


    private String defaultName="bucket-traceability";

    private String ip="http://192.168.4.14";

    private Integer port=8091;

    private String accessKey="minioadmin";

    private String secretKey="minioadmin";

    private String address="http://192.168.4.14:8091";

    /*private String defaultName="bucket-traceability";

    private String ip="http://172.16.10.61";

    private Integer port=9000;

    private String accessKey="admin";

    private String secretKey="123@abc.com";

    private String address="http://172.16.10.61:9000";*/




    public String uploadAndGetUrl(String objectName, InputStream input, String bucket) throws Exception {
    	return wosUploadAndGetUrl(objectName, input, bucket);
    }


    public InputStream getMediaByObjectName(String wosName, String bucket) throws Exception {
    	return wosGetMediaByObjectName(wosName, bucket);
    }

    public InputStream getMediaByObjectName(String wosName, String bucket,String  objStore) throws Exception {
    	return wosGetMediaByObjectName(wosName, bucket);
    }

    public String uploadAndGetUrl(String fileName, String filePath, String bucket) throws Exception {
    	return wosUploadAndGetUrl(fileName, filePath, bucket);
    }

    public File getFileByObjectName(String wosName, String bucket) throws Exception {
    	return wosGetFileByObjectName(wosName, bucket);
    }

    private File wosGetFileByObjectName(String wosName, String bucket) throws InvalidEndpointException, InvalidPortException, IOException, InvalidArgumentException, InvalidBucketNameException, InsufficientDataException, XmlPullParserException, ErrorResponseException, NoSuchAlgorithmException, NoResponseException, InvalidKeyException, InvalidResponseException, InternalException {
        String bucketName;
        if (ValidUtils.isNotNull(bucket)) {
            bucketName = bucket;
        } else {
            bucketName = defaultName;
        }
        WOSClient wosClient1 = new WOSClient(ip, port, accessKey, secretKey);
        String suffix = wosName.substring(wosName.lastIndexOf(".") + 1);
        String prefix = wosName.substring(0, wosName.lastIndexOf("."));

        File tempFile = File.createTempFile(prefix, "." + suffix);
        try (InputStream object = wosClient1.getObject(bucketName, wosName); FileOutputStream file = new FileOutputStream(tempFile.getAbsolutePath())) {
            int index;
            byte[] bytes = new byte[1024];
            while ((index = object.read(bytes)) != -1) {
                file.write(bytes, 0, index);
                file.flush();
            }
        }
        return tempFile;
    }

    public void deleteObject(String wosName, String bucket) throws Exception {
    	wosDeleteObject(wosName, bucket);
    }

    public void wosDeleteObject(String wosName, String bucket) throws Exception {
        String bucketName;
        if (ValidUtils.isNotNull(bucket)) {
            bucketName = bucket;
        } else {
            bucketName = defaultName;
        }
        WOSClient wosClient = new WOSClient(ip, port, accessKey, secretKey);
        wosClient.removeObject(bucketName, wosName);
    }
    public String initialUploadId(String bucketName, String wosName) throws Exception {
    	return wosInitialUploadId(bucketName, wosName);
    }

    public Part uploadPart(String bucketName, String objectName, InputStream data, String uploadId, int partNumber) throws Exception {
    	return wosUploadPart(bucketName, objectName, data, uploadId, partNumber);
    }

    public String completeMultipart(String bucketName, String objectName, String
            uploadId, Part[] parts) throws Exception {
    	return wosCompleteMultipart(bucketName, objectName, uploadId, parts);
    }




    private String wosUploadAndGetUrl(String fileName, InputStream input, String bucket) throws Exception {
        String url = null;
        String bucketName;

        // 使用测试服务的URL，端口9001，Access key和Secret key创建一个WOSClient对象
        WOSClient wosClient = new WOSClient(ip, port, accessKey, secretKey);
        if (ValidUtils.isNotNull(bucket)) {
            bucketName = bucket;
        } else {
            bucketName = defaultName;
        }

        // 检查存储桶是否已经存在
        wosCheckBucket(bucketName, wosClient);
        // 使用putObject上传一个文件到存储桶中。
        wosClient.putObject(bucketName, fileName, input, null);
        // 获取公有访问链接
        url = wosClient.getObjectOpenUrl(bucketName, fileName);
        if (ValidUtils.isNotNull(url)) {
            throw new Exception(fileName + "文件上传失败");
        }
        url=url.replace(ip + ":8387", address);
        log.info(url);
        return url;
    }

    private InputStream wosGetMediaByObjectName(String wosName, String bucket) throws InvalidEndpointException, InvalidPortException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, InvalidArgumentException, InvalidResponseException {
        String bucketName;
        if (ValidUtils.isNotNull(bucket)) {
            bucketName = bucket;
        } else {
            bucketName = defaultName;
        }
        log.info("*************************{}", bucketName);
        WOSClient wosClient1 = new WOSClient(ip, port, accessKey, secretKey);
        InputStream object = wosClient1.getObject(bucketName, wosName);
        return object;
    }


    public String wosUploadAndGetUrl(String fileName, String filePath, String bucket) throws Exception {
        String url = null;
        String bucketName;
        if (ValidUtils.isNotNull(bucket)) {
            bucketName = bucket;
        } else {
            bucketName = defaultName;
        }
        // 使用测试服务的URL，端口9001，Access key和Secret key创建一个WOSClient对象
        WOSClient wosClient = new WOSClient(ip, port, accessKey, secretKey);


        // 检查存储桶是否已经存在
        wosCheckBucket(bucketName, wosClient);
        // 使用putObject上传一个文件到存储桶中。
        wosClient.putObject(bucketName, fileName, filePath);
        // 获取公有访问链接
        url = wosClient.getObjectOpenUrl(bucketName, fileName);
        if (ValidUtils.isNotNull(url)) {
            throw new Exception(fileName + "文件上传失败");
        }
		url=url.replace(ip + ":8387", address);
		log.info(url);
		return url;
    }

    private String wosInitialUploadId(String bucketName, String wosName) throws InvalidEndpointException, InvalidPortException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, InvalidResponseException, RegionConflictException {
        bucketName = ValidUtils.isNotNull(bucketName) ? defaultName : bucketName;
        WOSClient wosClient = new WOSClient(ip, port, accessKey, secretKey);
        wosCheckBucket(bucketName, wosClient);
        String initialId = wosClient.initMultipartUpload(bucketName, wosName, null);
        return initialId;
    }

    private Part wosUploadPart(String bucketName, String objectName, InputStream data, String uploadId, int partNumber) throws InvalidEndpointException, InvalidPortException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, InvalidResponseException, IOException, XmlPullParserException, InvalidKeyException, RegionConflictException {
        bucketName = ValidUtils.isNotNull(bucketName) ? defaultName : bucketName;
        WOSClient wosClient = new WOSClient(ip, port, accessKey, secretKey);
        wosCheckBucket(bucketName, wosClient);
        Part part = wosClient.uploadPart(bucketName, objectName, data, uploadId, partNumber);
        return part;
    }

    private String wosCompleteMultipart(String bucketName, String objectName, String uploadId, Part[] parts) throws InvalidEndpointException, InvalidPortException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, InvalidResponseException, InvalidExpiresRangeException {
        bucketName = ValidUtils.isNotNull(bucketName) ? defaultName : bucketName;
        WOSClient wosClient = new WOSClient(ip, port, accessKey, secretKey);
        wosClient.completeMultipart(bucketName, objectName, uploadId, parts);
        String url = wosClient.getObjectOpenUrl(bucketName, objectName);
        return url.replace(ip + ":8387", address);
    }

    private void wosCheckBucket(String bucketName, WOSClient wosClient) throws InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException, InvalidResponseException, RegionConflictException {
        boolean isExist = wosClient.bucketExists(bucketName);
        if (isExist) {
            log.info("Bucket already exists.");
        } else {
            // 创建一个名为bucket1的存储桶。
            wosClient.makeBucket(bucketName);
        }
    }
}
