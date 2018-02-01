package com.fudanse.apiknowledgegraph.persistence;

import static org.neo4j.driver.v1.Values.parameters;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;

import com.alibaba.fastjson.JSON;
import com.fudanse.apiknowledgegraph.enums.EnumVertexLabel;
import com.fudanse.apiknowledgegraph.model.Edge;
import com.fudanse.apiknowledgegraph.model.EntryMethod;
import com.fudanse.apiknowledgegraph.model.Listener;
import com.fudanse.apiknowledgegraph.model.Method;
import com.fudanse.apiknowledgegraph.model.Vertex;
import com.fudanse.apiknowledgegraph.utils.Neo4JUtil;

public class VertexDAO implements IVertexDAO {

	@Override
	public void saveVertex(Vertex vertex) {
		Driver driver = Neo4JUtil.getDriver();
		Session session = Neo4JUtil.getSession(driver);
		Integer id = session.writeTransaction(new TransactionWork<Integer>() {

			@Override
			public Integer execute(Transaction tx) {
				StatementResult result = tx.run(GenerateCypher.saveVertex(vertex), parameters("name", vertex.getName(),
						"description", vertex.getDescription(), "simpleDescription", vertex.getSimpleDescription()));
				return result.single().get(0).asInt();
			}

		});
		vertex.setId(id);
		Neo4JUtil.closeSession(session);
		Neo4JUtil.closeDriver(driver);
	}

	@Override
	public void saveEdge(Vertex left, Vertex right, Edge edge) {
		Driver driver = Neo4JUtil.getDriver();
		Session session = Neo4JUtil.getSession(driver);
		session.run(GenerateCypher.saveEdge(left, right, edge));
		Neo4JUtil.closeSession(session);
		Neo4JUtil.closeDriver(driver);

	}

	@Override
	public List<Vertex> selectByLabel(String label) {
		List<Vertex> vertexs = new ArrayList<>();
		Driver driver = Neo4JUtil.getDriver();
		Session session = Neo4JUtil.getSession(driver);
		StatementResult result = session.run(GenerateCypher.getByLabel(label));
		List<Record> record = result.list();
		for(Record r : record) {
			Value value = r.get(0);
			Node n = value.asNode();
			String json = JSON.toJSON(n.asMap()).toString();
			Vertex v = JSON.parseObject(json,Vertex.class);
			v.setLabel(label);
			vertexs.add(v);
		}
		Neo4JUtil.closeSession(session);
		Neo4JUtil.closeDriver(driver);
		return vertexs;
	}

	@Override
	public List<Listener> selectListenerByViewName(String viewName) {
		List<Listener> lis = new ArrayList<>();
		Driver driver = Neo4JUtil.getDriver();
		Session session = Neo4JUtil.getSession(driver);
		StatementResult result = session.run(GenerateCypher.getByViewName(viewName, EnumVertexLabel.LISTENER.getValue()));
		List<Record> record = result.list();
		for(Record r : record) {
			Value value = r.get(0);
			Node n = value.asNode();
			String json = JSON.toJSON(n.asMap()).toString();
			Listener l = JSON.parseObject(json,Listener.class);
			lis.add(l);
		}
		Neo4JUtil.closeSession(session);
		Neo4JUtil.closeDriver(driver);
		return lis;
	}

	@Override
	public List<Method> selectMethodByViewName(String viewName) {
		List<Method> methods = new ArrayList<>();
		Driver driver = Neo4JUtil.getDriver();
		Session session = Neo4JUtil.getSession(driver);
		StatementResult result = session.run(GenerateCypher.getByViewName(viewName, EnumVertexLabel.METHOD.getValue()));
		List<Record> record = result.list();
		for(Record r : record) {
			Value value = r.get(0);
			Node n = value.asNode();
			String json = JSON.toJSON(n.asMap()).toString();
			Method m = JSON.parseObject(json,Method.class);
			methods.add(m);
		}
		Neo4JUtil.closeSession(session);
		Neo4JUtil.closeDriver(driver);
		return methods;
	}

	@Override
	public List<EntryMethod> selectEntryMethodByListener(List<Listener> lis) {
		List<EntryMethod> entryMethod = new ArrayList<>();
		Driver driver = Neo4JUtil.getDriver();
		Session session = Neo4JUtil.getSession(driver);
		StatementResult result = session.run(GenerateCypher.getEntryMethodByListener(lis));
		List<Record> record = result.list();
		for(Record r : record) {
			Value value = r.get(0);
			Node n = value.asNode();
			String json = JSON.toJSON(n.asMap()).toString();
			EntryMethod e = JSON.parseObject(json,EntryMethod.class);
			entryMethod.add(e);
		}
		return entryMethod;
	}
	
	public static void main(String[] args) {
		VertexDAO v = new VertexDAO();
		v.selectByLabel("View").forEach((n)->System.out.println(n.toString()));
	}

}
