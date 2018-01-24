package com.fudanse.apiknowledgegraph.persistence;

import static org.neo4j.driver.v1.Values.parameters;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import com.fudanse.apiknowledgegraph.model.Edge;
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

}
