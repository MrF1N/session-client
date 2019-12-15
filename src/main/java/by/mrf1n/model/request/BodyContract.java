package by.mrf1n.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Смотри сервер
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BodyContract extends EmptyContract {
    private Object body;
}
