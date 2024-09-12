package business.service;

import business.entity.Pagination;

import java.util.ArrayList;
import java.util.List;

public class PaginationService {
    public static <T> Pagination<T> paginate(List<T> allItems, int currentPage, int pageSize) {
        int totalItems = allItems.size();
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);

        List<T> pageItems = new ArrayList<>();
        if (start < totalItems) {
            pageItems = allItems.subList(start, end);
        }
        return new Pagination<>(pageItems, totalItems, currentPage, pageSize);
    }
}
