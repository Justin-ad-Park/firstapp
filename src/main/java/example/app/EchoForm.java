package example.app;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class EchoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 20)
    private String text;

}
