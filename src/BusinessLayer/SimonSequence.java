package BusinessLayer;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SimonSequence {

    private Iterator<Integer> iterator;
    private static final Random r = new Random();
    private Map<Integer, Color> colorsMap = new HashMap<>(){
        {
            put(0, Color.RED);
            put(1, Color.BLUE);
            put(2, Color.GREEN);
            put(3, Color.YELLOW);
        }
    };
    private List<Integer> colorsList;
    private User user;

    public SimonSequence(User user){
        this.user = user;
        colorsList = new LinkedList<>();
    }

    public void addRandomly(){
        colorsList.add(r.nextInt(4));
        iterator = colorsList.iterator();
    }

    public boolean isMatching(int choose){
        if(iterator.hasNext()){
            return iterator.next() == choose;
        }
        return true;
    }

    public boolean isSequenceOver(){
        return !iterator.hasNext();
    }

    public void endSequence(){
        iterator = colorsList.iterator();
    }

    public List<Integer> getSequence(){
        return colorsList;
    }

    public Map<Integer, Color> getColorsMap(){
        return colorsMap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
