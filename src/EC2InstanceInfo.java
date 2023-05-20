import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class EC2InstanceInfo {
    public static void main(String[] args) {
    	final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();
    	boolean done = false;

    	DescribeInstancesRequest request = new DescribeInstancesRequest();
    	while(!done) {
    	    DescribeInstancesResult response = ec2.describeInstances(request);

    	    for(Reservation reservation : response.getReservations()) {
    	        for(Instance instance : reservation.getInstances()) {
    	            System.out.printf(
    	                "Found instance with id %s, " +
    	                "AMI %s, " +
    	                "type %s, " +
    	                "state %s " +
    	                "and monitoring state %s",
    	                instance.getInstanceId(),
    	                instance.getImageId(),
    	                instance.getInstanceType(),
    	                instance.getState().getName(),
    	                instance.getMonitoring().getState());
    	        }
    	    }

    	    request.setNextToken(response.getNextToken());

    	    if(response.getNextToken() == null) {
    	        done = true;
    	    }
    	}
    }
}
