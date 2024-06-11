JAVAC = javac

#JAVAFLAGS

SOURCES = Resource.java Building.java Manager.java People.java Resident.java Worker.java UI.java 
OUTPUT = UI

all: $(OUTPUT)

$(OUTPUT): $(SOURCES)
	$(JAVAC) $(SOURCES)


clean:
	rm -f *.class

run: $(OUTPUT)
	java $(OUTPUT)

doc: $(SOURCES)
	javadoc $(SOURCES) 
