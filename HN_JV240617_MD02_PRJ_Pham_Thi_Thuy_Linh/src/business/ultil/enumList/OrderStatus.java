package business.ultil.enumList;

public enum OrderStatus {
    WAITING("Đơn hàng mới chờ xác nhận"),
    CONFIRM("Đã xác nhận"),
    DELIVERY("Đang giao hàng"),
    SUCCESS("Đã giao hàng"),
    CANCEL("Đã hủy đơn"),
    DENIED("Bị từ chối");
//cancel vs denid nho cong lai hnag vao gio
    private final String status;
    // Constructor enum riêng tư
    OrderStatus(String status) {
        this.status = status;
    }
    // Phương thức để lấy mô tả của trạng thái
    public String getStatus() {
        return status;
    }
    public String toString() {
        return name().charAt(0)+name().substring(1).toLowerCase();
    };
}
