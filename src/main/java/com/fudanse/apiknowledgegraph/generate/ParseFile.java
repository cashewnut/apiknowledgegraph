package com.fudanse.apiknowledgegraph.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fudanse.apiknowledgegraph.model.Event;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Method;
import com.fudanse.apiknowledgegraph.model.MethodEventPair;
import com.fudanse.apiknowledgegraph.model.SubAction;
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
				String name = strs[0].split("[.]")[1];
				l.setName(name);
				l.setDescription(strs[1]);
				l.setBelongto("");
				lis.add(l);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lis;
	}

	public static List<Listener> getListener(String filePath) {
		List<Listener> lis = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				Listener l = new Listener();
				String name = strs[0];
				l.setName(name);
				l.setDescription(strs[1]);
				l.setBelongto(strs[0].split("[.]")[0]);
				lis.add(l);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lis;
	}

	public static List<Event> getEvent(String filePath) {
		List<Event> events = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				Event e = new Event(strs[0], strs[1]);
				events.add(e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return events;
	}

	public static List<MethodEventPair> getMethodEventPair(String filePath) {
		List<MethodEventPair> meps = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				MethodEventPair e = new MethodEventPair(strs[0], strs[1]);
				meps.add(e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return meps;
	}

	public static List<Method> getMethod(String filePath) {
		List<Method> methods = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				Method m = new Method(strs[0], strs[1], strs[2]);
				if (!methods.contains(m))
					methods.add(m);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return methods;
	}

	public static List<SubAction> getSubAction(String filePath) {
		List<SubAction> subActions = new ArrayList<>();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] strs = line.split(",");
				SubAction sa = new SubAction(strs[0], strs[1], strs[2]);
				subActions.add(sa);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return subActions;
	}

}
