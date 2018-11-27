package example.app;

import lombok.Data;
import java.io.Serializable;

@Data
public class EchoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;

}
