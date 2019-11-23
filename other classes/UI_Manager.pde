public class UI_Manager implements Displayable {

    PShape UIelements; 

    public UI_Manager () { 

        UIelements = createShape(GROUP);

    }
    PShape getShape(){
        return UIelements;
        
    }
    
    void updateShape(PShape newpattern){
    
    }
}
 