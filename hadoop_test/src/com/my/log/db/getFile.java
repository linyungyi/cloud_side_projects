package com.my.log.db;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class getFile {
	public void getContent() throws IOException, URISyntaxException{
		FileSystem fs=FileSystem.get(new URI("hdfs://127.0.0.1:9000"),new Configuration());
		FileStatus[] flist=fs.listStatus(new Path("/user/00000"));

		FSDataInputStream fsdatainput=fs.open(flist[0].getPath());
		BufferedReader bfr=new BufferedReader(new InputStreamReader(fsdatainput));
		while(bfr.ready())
		{
		System.out.println(bfr.readLine());
		}

	}
	public static void main(String[] args) throws Exception {
		getFile gf=new getFile();
		gf.getContent();
	}
}