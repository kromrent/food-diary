// src/AddProductForm.js
import React, { useState } from "react";

function AddProductForm({ onAdd }) {
  const [formData, setFormData] = useState({
    name: "",
    calories: "",
    protein: "",
    fat: "",
    carbs: "",
  });

  const handleChange = (e) => {
    setFormData({ 
      ...formData, 
      [e.target.name]: e.target.value 
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const response = await fetch("http://localhost:8080/api/products", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: formData.name,
        calories: parseFloat(formData.calories),
        protein: parseFloat(formData.protein),
        fat: parseFloat(formData.fat),
        carbs: parseFloat(formData.carbs),
      }),
    });

    if (response.ok) {
      const newProduct = await response.json();
      onAdd(newProduct); 
      setFormData({
        name: "",
        calories: "",
        protein: "",
        fat: "",
        carbs: "",
      });
    } else {
      alert("Ошибка при добавлении продукта");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Добавить продукт</h2>
      <input
        type="text"
        name="name"
        placeholder="Название"
        value={formData.name}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="calories"
        placeholder="Калории"
        value={formData.calories}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="protein"
        placeholder="Белки"
        value={formData.protein}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="fat"
        placeholder="Жиры"
        value={formData.fat}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="carbs"
        placeholder="Углеводы"
        value={formData.carbs}
        onChange={handleChange}
        required
      />
      <button type="submit">Добавить</button>
    </form>
  );
}

export default AddProductForm;
