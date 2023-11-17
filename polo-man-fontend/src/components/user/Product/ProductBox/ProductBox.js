import { Link } from "react-router-dom";
import "./ProductBox.css";

const ProductBox = ({ sanpham }) => {
  return (
    <div className="product-box">
      <Link to={`/sanpham/${sanpham.masanpham}`}>
        <div className="img-wrapper">
          <img
            className="img-fluid product-image"
            src={`//public/img/sanpham/images (1).jpg${sanpham.hinhanh}`}
            alt={sanpham.tensanpham}
          />
         
        </div>
        <div className="product-detail">
          <h6>{sanpham.tensanpham}</h6>
          <div className="prices">
            <h4>
              <span className="price-cost">{sanpham.giaban}</span>
              <span className="ml-1 discount">
                ({sanpham.promotion_percent || 0}%)
              </span>
            </h4>
            <h4 className="sale-price">{sanpham.giaban}</h4>
          </div>
        </div>
      </Link>
    </div>
  );
};

export { ProductBox };
