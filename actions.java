import java.util.ArrayList;

public class actions {
    
    public void move(monkey monkey, room from, room to)
    {
        if()
        monkey.changeLocation(null);
    }

    private boolean checkPreconditions(String input, room from, room to, monkey monkey)
    {
        switch(input)
        {
            case "move":
                return movePreconditions(from, to);
            case "push":
                return pushPreconditions(from, to);
            case "climbup":
                return climbupPreconditions(monkey);
        }
        return false;
    }

    private boolean movePreconditions(room from, room to)
    {
        if(from.isAdjacentTo(to) && from.getItems().contains(monkey.class))
        {
            return true;
        }
        else{return false;}
    }

    private boolean pushPreconditions(room from, room to)
    {
        if(from.getItems().contains(box.class) && from.getItems().contains(monkey.class))
        {
            return true;
        }else{return false;}
    }

    private boolean climbupPreconditions(room room)
    {
        if(room.getItems().contains(monkey.class) && room.getItems().contains(box.class))
        {
            if()
        }
    }
}
