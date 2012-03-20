package no.kantega.kriska.conversation;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class ConversationRepository {
	private EmbeddedGraphDatabase graphDb;
	Index<Node> nodeIndex;

	public ConversationRepository() {
		graphDb = new EmbeddedGraphDatabase("target/database/location");

		registerShutdownHook(graphDb);

	}

	public String speakSentence(String sentence) {
		Node firstNode;

		Transaction tx = graphDb.beginTx();
		try {

			firstNode = graphDb.createNode();
			firstNode.setProperty("message", "Hello, ");
			graphDb.getReferenceNode().createRelationshipTo(firstNode,
					RelTypes.KNOWS);

			Relationship singleRelationship = graphDb.getReferenceNode()
					.getSingleRelationship(RelTypes.LAST_SENTENCE,
							Direction.OUTGOING);
			if (singleRelationship != null) {
				singleRelationship.delete();
			}
			graphDb.getReferenceNode().createRelationshipTo(firstNode,
					RelTypes.LAST_SENTENCE);

			tx.success();
		} finally {
			tx.finish();
		}

		return "answer";
	}

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}

	private static enum RelTypes implements RelationshipType {
		KNOWS, LAST_SENTENCE
	}

}
