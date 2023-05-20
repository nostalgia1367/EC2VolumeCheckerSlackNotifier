import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVolumesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeVolumesResponse;
import software.amazon.awssdk.services.ec2.model.Volume;

public class EC2VolumeInfo {
    public static void main(String[] args) {
        DefaultCredentialsProvider credentialsProvider = DefaultCredentialsProvider.create();
        Region region = Region.AP_NORTHEAST_2;

        Ec2Client ec2Client = Ec2Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        // 볼륨 정보 조회
        DescribeVolumesRequest request = DescribeVolumesRequest.builder().build();
        DescribeVolumesResponse response = ec2Client.describeVolumes(request);
        for (Volume volume : response.volumes()) {
            System.out.println("Volume ID: " + volume.volumeId());
            System.out.println("State: " + volume.state());
            System.out.println("Size: " + volume.size());
            System.out.println("Availability Zone: " + volume.availabilityZone());
            System.out.println();
        }

        // EC2 클라이언트 종료
        ec2Client.close();
    }
}
