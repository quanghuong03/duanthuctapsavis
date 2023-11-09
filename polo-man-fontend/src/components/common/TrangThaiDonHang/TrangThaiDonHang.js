const Trang_Thai_Don_Hang = [
  { value: 1, label: "Chờ xác nhận" },
  { value: 2, label: "Đang giao hàng" },
  { value: 3, label: "Hoàn thành" },
  { value: 4, label: "Hủy" },
  { value: 5, label: "Yêu cầu hủy" },
  { value: 6, label: "Đã xác nhận" },
  { value: 7, label: "Đang chuẩn bị hàng" },
];

const Trang_Thai_Don_Hang_Map = Trang_Thai_Don_Hang.reduce((acrr, pre) => {
  return {
    ...acrr,
    [pre.value]: pre.label,
  };
}, {});
export { Trang_Thai_Don_Hang, Trang_Thai_Don_Hang_Map };
