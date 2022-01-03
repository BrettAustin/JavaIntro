public class ValidOutputItem<T> {

    private T item;

    public ValidOutputItem(){
        item = null;
    }

    public ValidOutputItem(T item){
        this();
        if(isValid(item)) {
            this.item = item;
        }
    }

    private boolean isValid(T item){
        String itemString = item.toString();
        boolean valid = true;
        for(int i = 0; i<itemString.length() && valid;i++){
            char currentCharacter = itemString.charAt(i);
            boolean passTest = Character.isLetterOrDigit(currentCharacter) || currentCharacter==' ';
            if(!passTest){
                valid = false;
            }
            return valid;
        }
    }

    public boolean isEmpty(){
        return item==null;
    }

    public void processString(){

    }
}



