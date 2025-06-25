import React, { useState, useEffect } from "react";
import axios from "axios";

function FoodEntryForm() {
  const [users, setUsers] = useState([]);
  const [products, setProducts] = useState([]);
  const [selectedUserMacros, setSelectedUserMacros] = useState(null);

  const [form, setForm] = useState({
    userId: "",
    productId: "",
    weight: "",
    date: ""
  });

  useEffect(() => {
    axios.get("http://localhost:8080/api/users").then(res => {
      setUsers(res.data);
      // Если выбран пользователь, загружаем его макросы
      if (form.userId) {
        axios.get(`http://localhost:8080/api/users/${form.userId}`)
          .then(res => setSelectedUserMacros(res.data.macros))
          .catch(err => console.error("Ошибка при загрузке макросов:", err));
      }
    });
    axios.get("http://localhost:8080/api/products").then(res => setProducts(res.data));
  }, [form.userId]); // Обновляем при изменении userId

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/api/food-entries", form)
      .then(() => alert("Запись добавлена!"));
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Добавить запись о еде</h2>

      <label>Пользователь:</label>
      <select name="userId" value={form.userId} onChange={handleChange}>
        <option value="">Выбери пользователя</option>
        {users.map(user => (
          <option key={user.id} value={user.id}>{user.name}</option>
        ))}
      </select>

      {selectedUserMacros && (
        <div>
          <h3>Рекомендуемые макронутриенты:</h3>
          <p>Белки: {selectedUserMacros.protein?.toFixed(1)} г</p>
          <p>Жиры: {selectedUserMacros.fat?.toFixed(1)} г</p>
          <p>Углеводы: {selectedUserMacros.carbs?.toFixed(1)} г</p>
        </div>
      )}

      <label>Продукт:</label>
      <select name="productId" value={form.productId} onChange={handleChange}>
        <option value="">Выбери продукт</option>
        {products.map(prod => (
          <option key={prod.id} value={prod.id}>{prod.name}</option>
        ))}
      </select>

      <label>Вес (г):</label>
      <input type="number" name="weight" value={form.weight} onChange={handleChange} />

      <label>Дата:</label>
      <input type="date" name="date" value={form.date} onChange={handleChange} />

      <button type="submit">Добавить</button>
    </form>
  );
}

export default FoodEntryForm;