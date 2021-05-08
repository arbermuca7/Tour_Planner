package sample.businessLayer.javaApp;

public class JavaAppManagerFactory {
    private static JavaAppManager manager;

    public  static JavaAppManager GetManager(){
        if(manager == null){
            manager = new JavaAppManagerImpl();
        }
        return manager;
    }
}
