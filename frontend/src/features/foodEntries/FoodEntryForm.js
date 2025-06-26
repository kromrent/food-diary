import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { FoodEntryContext } from "../context/FoodEntryContext";

function FoodEntryForm() {
  const { addEntry } = useContext(FoodEntryContext);
  const [products, setProducts] = useState([]);
  const [selectedUserMacros, setSelectedUserMacros] = useState(null);
  const [userId, setUserId] = useState(null);
  const token = localStorage.getItem("token");

  const [form, setForm] = useState({
    productId: "",
    weight: "",
    date: ""
  });

  useEffect(() => {
    if (!token) return;

    axios.get("http://localhost:8080/api/users/me", {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(res => {
        setUserId(res.data.id);
        setSelectedUserMacros(res.data.macros);
      })
      .catch(err => console.error("Ошибка загрузки текущего пользователя:", err));

    axios.get("http://localhost:8080/api/products", {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(res => {
        console.log("Продукты с бэка:", res.data);
        setProducts(res.data);
      })
      .catch(err => console.error("Ошибка загрузки продуктов:", err));
  }, [token]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      ...form,
      userId: userId
    };

    axios.post("http://localhost:8080/api/food-entries", payload, {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(response => {
        alert("Запись добавлена!");
        setForm({ productId: "", weight: "", date: "" });
        addEntry(response.data); 
      })
      .catch(err => console.error("Ошибка при добавлении записи:", err));
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Добавить запись о еде</h2>

      {selectedUserMacros && (
        <div>
          <h3>Ваши рекомендуемые макронутриенты:</h3>
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
      <input
        type="number"
        name="weight"
        value={form.weight}
        onChange={handleChange}
      />

      <label>Дата:</label>
      <input
        type="date"
        name="date"
        value={form.date}
        onChange={handleChange}
      />

      <button type="submit">Добавить</button>
    </form>
  );
}

export default FoodEntryForm;