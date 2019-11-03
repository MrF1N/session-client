package by.mrf1n.model.request;

import by.mrf1n.model.CommandType;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmptyContract implements Serializable {
    private CommandType commandType;
}
