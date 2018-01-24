package com.fudanse.apiknowledgegraph.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.View;

public class ParseFile {

	public static List<View> generateView(String filePath) {
		List<View> views = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				View v = new View(strs[0], strs[1], strs[2]);
				views.add(v);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return views;
	}

	public static List<Listener> getCommonListener(String filePath) {
		List<Listener> lis = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				Listener l = new Listener();
				String name = strs[0].split(",")[1];
				l.setName(name);
				l.setDescription(strs[1]);
				l.setBelongto("View");
				lis.add(l);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lis;
	}
	
	public static List<Listener> getListener(String filePath){
		List<Listener> lis = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				Listener l = new Listener();
				String name = strs[0].split(",")[1];
				l.setName(name);
				l.setDescription(strs[1]);
				l.setBelongto(strs[0].split(",")[0]);
				lis.add(l);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lis;
	}

}
