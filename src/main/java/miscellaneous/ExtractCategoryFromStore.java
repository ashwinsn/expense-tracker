package miscellaneous;

import db.mappers.StoreCategoryMapper;
import db.pojos.StoreCategory;

import java.util.List;

/**
 * Created by anatarajan on 6/26/16.
 */
public class ExtractCategoryFromStore {

    List<StoreCategory> storeCategoryList;
    private static ExtractCategoryFromStore currentInstance;

    private ExtractCategoryFromStore() {}

    public static ExtractCategoryFromStore getInstance(){
        if(currentInstance == null){
            currentInstance = new ExtractCategoryFromStore();
        }
        return currentInstance;
    }

    public void refreshStoreCategoryList(){
        storeCategoryList = StoreCategoryMapper.getInstance().getAll();
    }

    public String execute(String store){
        for(StoreCategory storeCategory : storeCategoryList){
            if(store.contains(storeCategory.getStore())){
                return storeCategory.getCategory();
            }
        }
        return null;
    }
}
