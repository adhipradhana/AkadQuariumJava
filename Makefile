JVC = javac
SRC = src/itb/akadquarium/
DEP = $(SRC)Aquarium.java $(SRC)AquariumObject.java $(SRC)Coin.java $(SRC)Fish.java $(SRC)FishFood.java $(SRC)Guppy.java $(SRC)LinkedList.java \
$(SRC)Main.java $(SRC)Node.java $(SRC)Piranha.java $(SRC)Snail.java

main : $(DEP)
	$(JVC) $(DEP) -d out