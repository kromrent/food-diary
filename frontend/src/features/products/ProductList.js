
import React, { useEffect, useState } from "react";
import axios from "axios";

const ProductList = () => {
  const [products, setProducts] = useState([]);

 
  useEffect(() => {
    axios.get("http://localhost:8080/api/products")
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error("Ошибка при получении продуктов:", error);
      });
  }, []);

  return (
    <div>
      <h2>Список продуктов</h2>
      <table>
        <thead>
          <tr>
            <th>Название</th>
            <th>Ккал</th>
            <th>Белки</th>
            <th>Жиры</th>
            <th>Углеводы</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>{product.name}</td>
              <td>{product.calories}</td>
              <td>{product.protein}</td>
              <td>{product.fat}</td>
              <td>{product.carbs}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
  useEffect(() => {
  axios.get("http://localhost:8080/api/products")
    .then(response => {
      console.log("Получено:", response.data); 
      setProducts(response.data);
    })
    .catch(error => {
      console.error("Ошибка при получении продуктов:", error);
    });
}, []);
};

export default ProductList;