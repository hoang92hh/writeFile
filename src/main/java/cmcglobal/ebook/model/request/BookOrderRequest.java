package cmcglobal.ebook.model.request;

public class BookOrderRequest {

    private String isbnCode;
    private int quantityOfBookOrder;

    public BookOrderRequest(String isbnCode, int quantityOfBookOrder) {
        this.isbnCode = isbnCode;
        this.quantityOfBookOrder = quantityOfBookOrder;
    }

    public BookOrderRequest() {
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public int getQuantityOfBookOrder() {
        return quantityOfBookOrder;
    }

    public void setQuantityOfBookOrder(int quantityOfBookOrder) {
        this.quantityOfBookOrder = quantityOfBookOrder;
    }
}
