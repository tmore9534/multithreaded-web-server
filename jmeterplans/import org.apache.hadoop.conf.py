import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxConsumptionDriver {
    
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: MaxConsumptionDriver <input path> <output path>");
            System.exit(-1);
        }
        
        // Set up the configuration
        Configuration conf = new Configuration();
        
        // Create a new job
        Job job = Job.getInstance(conf, "Max Electrical Consumption per Year");
        
        // Set the Jar by class
        job.setJarByClass(MaxConsumptionDriver.class);
        
        // Set Mapper and Reducer classes
        job.setMapperClass(MaxConsumptionMapper.class);
        job.setReducerClass(MaxConsumptionReducer.class);
        
        // Set the output types for Mapper and Reducer
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        // Wait for the job to complete
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}