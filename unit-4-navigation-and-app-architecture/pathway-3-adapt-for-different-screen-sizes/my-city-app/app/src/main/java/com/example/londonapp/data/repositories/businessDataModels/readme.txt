If the model objects retrieved from the data sources contained additional information that my app
did not need I would create model classes with a subset of information from the data source model
classes in the data layer, and then pass the business/problem-domain model classes created in the
data layer to the other layers of the UI system, however in this case my data source model classes
contain information which the rest of my app expects so I don't need to create new models to be used
in my app.
