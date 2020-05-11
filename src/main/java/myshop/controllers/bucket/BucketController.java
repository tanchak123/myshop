package myshop.controllers.bucket;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Bucket;
import myshop.model.Product;
import myshop.service.BucketService;

public class BucketController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final BucketService bucketService =
            (BucketService)injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Bucket> buckets = bucketService.getAll();
        List<List<String>> names = buckets.stream().map(Bucket::getProducts)
                .collect(Collectors.toList()).stream()
                .map(products1 -> products1.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        req.setAttribute("buckets", buckets);
        req.setAttribute("names", names);
        req.getRequestDispatcher("/WEB-INF/views/buckets/buckets.jsp").forward(req, resp);
    }
}
