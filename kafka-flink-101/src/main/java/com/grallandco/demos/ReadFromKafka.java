/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grallandco.demos;


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import java.util.Properties;

public class ReadFromKafka {


static int i=0;
  public static void main(String[] args) throws Exception {
    
// create execution environment
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    properties.setProperty("group.id", "flink_consumer");


    DataStream<String> stream = env
            .addSource(new FlinkKafkaConsumer09<>("flink-demo", new SimpleStringSchema(), properties));
    stream.map(new MapFunction<String, String>() {
      private static final long serialVersionUID = -6867736771747690202L;
      @Override
      public String map(String value) throws Exception {
	int temperature=Integer.parseInt(value);
	String warning= temperature > 50 ? "yes":"no";
	
	String jsonData="{\"temperature\":\""+value+"\",\"overheat\":\""+warning+"\"}";
        return jsonData;      
}
    }).addSink(new SinkFunction<String>(){
	@Override
	public void invoke(String mes) throws Exception{
	System.out.println("Stream value ==> "+mes);
	new Curl("http://localhost:9200/temperature/doc/"+i+"?pretty").sendToES(mes);
i++;
}

});

    env.execute();
  }

}
