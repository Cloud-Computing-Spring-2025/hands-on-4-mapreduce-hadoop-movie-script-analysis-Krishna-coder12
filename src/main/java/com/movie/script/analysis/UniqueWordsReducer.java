package com.movie.script.analysis;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Set;
import java.io.IOException;
import java.util.HashSet;

public class UniqueWordsReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set<String> uniqueWords = new HashSet<>();
        for (Text value : values) {
            String[] words = value.toString().split(",");
            for (String word : words) {
                uniqueWords.add(word);
            }
        }
        context.write(key, new Text(uniqueWords.toString()));
    }
}
