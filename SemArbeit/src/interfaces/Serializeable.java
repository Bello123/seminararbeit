package interfaces;

/**
 *
 * @author Daniel
 */
public interface Serializeable {
    
    void serialize(String path) throws Exception;
    
    void deserialize(String path) throws Exception;
    
}
