package by.mrf1n.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BodyContract extends EmptyContract {
    private Object body;
}
