import React, { useEffect, useState } from 'react';
import axios from 'axios';

function FoodEntryList() {
  const [entries, setEntries] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/food-entries")
      .then(response => setEntries(response.data))
      .catch(error => console.error("Ошибка при получении записей:", error));
  }, []);

  return (
    <div>
      <h2>Записи питания</h2>
      <table>
        <thead>
          <tr>
            <th>Пользователь</th>
            <th>Продукт</th>
            <th>Вес (г)</th>
            <th>Дата</th>
          </tr>
        </thead>
        <tbody>
          {entries.map((entry) => (
            <tr key={entry.id}>
              <td>{entry.userName}</td>
              <td>{entry.productName}</td>
              <td>{entry.weight}</td>
              <td>{entry.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default FoodEntryList;