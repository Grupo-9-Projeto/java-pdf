package school.sptech;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public class S3Provider {

    private final AwsCredentialsProvider credentials;

    public S3Provider() {
        this.credentials = DefaultCredentialsProvider.create();
    }

    public S3Client getS3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentials)
                .build();
    }

    public Bucket getBucket(){
        S3Client s3Client = getS3Client();
        List<Bucket> buckets = s3Client.listBuckets().buckets();
        for (Bucket bucket : buckets) {
            System.out.println("Bucket: " + bucket.name());
            if(bucket.name().equalsIgnoreCase("datalake-argos")){
                return bucket;
            }
        }
        return null;
    }

    public List<S3Object> listarObjetos(Bucket bucket){
        S3Client s3Client = getS3Client();
        ListObjectsRequest listObjects = ListObjectsRequest.builder()
                .bucket(bucket.name())
                .prefix("client/")
                .build();

        List<S3Object> objects = s3Client.listObjects(listObjects).contents();
        for (S3Object object : objects) {
            System.out.println("Objeto: " + object.key());
        }
        return objects;
    }
}
