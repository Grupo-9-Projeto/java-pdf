package school.sptech;

import school.sptech.exceptions.DadoinvalidoException;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
            if(bucket.name().equalsIgnoreCase("bucket-argos-teste")){
                return bucket;
            }
        }
        return null;
    }

    public List<S3Object> listarObjetos(Bucket bucket, String mac, String data){
        String[] partes = data.split("/");
        S3Client s3Client = getS3Client();
        ListObjectsRequest listObjects = ListObjectsRequest.builder()
                .bucket(bucket.name())
                .prefix("client/dispositivos/capturas/" + mac + "/" + partes[0] + "/" + partes[1] + "/" + partes[2])
                .build();

        List<S3Object> objects = s3Client.listObjects(listObjects).contents();

        if(objects.isEmpty()){
            throw new DadoinvalidoException("mac ou data não existem");
        }
        for (S3Object object : objects) {
            System.out.println("Objeto: " + object.key());
        }
        return objects;
    }

    public void baixarArquivo(S3Object objeto) {
        S3Client s3Client = getS3Client();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucket-argos-teste")
                .key(objeto.key())
                .build();

        String caminhoLocal = "downloads/" + objeto.key().replace(":", "-");

        try (InputStream objectContent = s3Client.getObject(
                getObjectRequest,
                ResponseTransformer.toInputStream()
        )) {
            File arquivo = new File(caminhoLocal);

            arquivo.getParentFile().mkdirs();

            Files.copy(
                    objectContent,
                    arquivo.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
