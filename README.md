# Semantic_web1
Create in-memory and persisted models with Jena.

The Resource Description Framework (RDF) is a standard (technically a W3C Recommendation) for describing resources. Resources are anything that we can identify.Resources have properties. In the code, some of the properties are Full Name, URI, contact_no, family name etc. Each property has a value called literal. Jena is a Java API which can be used to create and manipulate RDF graphs like this one and in Jena, a graph is called a model. So the problem asks us to create in-memory and persisted models. 

In-memory model :
First of all we define the literal values of properties so that we can use them at later stage. Then I created an empty Model or model, using the ModelFactory method createDefaultModel() to create a memory-based model. 
Model model = ModelFactory.createDefaultModel();
Then the resource is created and properties are added to it using createResource() and addProperty() method.
Then finally we can write this model in various formats such as RDF/XML-ABBREV, N-TRIPLES, N3 etc.These can be directly printed on console or be written into file using fileWriter. E.g. If we want to print it in N3 format.
model.write(new BufferedWriter(new FileWriter("Lab1p2_acd170130.n3")), "N3");
Here I am saying this is in-memory model as we are storing the resource and properties of it in the memory and using it from there.

TDB Persistent model :
TDB is a component of Jena for RDF storage and query. It support the full range of Jena APIs. TDB can be used as a high performance RDF store on a single machine. A TDB store can be accessed and managed with the provided command line scripts and via the Jena API. The class TDBFactory contains the static factory methods for creating and connecting to a TDB-backed graph or an RDF dataset. This database is created at the directory location given by us. 
Dataset dataset=TDBFactory.createDataset("MyDatabases/Dataset1"); // MyDatabases/Dataset1 is the directory where database resides.
For the assignment’s purpose, I have used rdf contents of my FOAF profile. It is then read into “myrdf” model of tdb and from that present the model in various models just as above.
