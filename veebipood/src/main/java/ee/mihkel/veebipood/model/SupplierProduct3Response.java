package ee.mihkel.veebipood.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SupplierProduct3Response {
    private ArrayList<SupplierProduct3> products;
    private int total;
    private int skip;
    private int limit;
}
