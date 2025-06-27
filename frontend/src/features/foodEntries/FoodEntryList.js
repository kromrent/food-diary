import React, { useContext } from 'react';
import { FoodEntryContext } from "../context/FoodEntryContext";

function FoodEntryList() {
  const { entries } = useContext(FoodEntryContext);

  return (
    <div>
      <h2>Ваши записи</h2>
      <table>
        <thead>
          <tr>
            <th>Продукт</th>
            <th>Вес (г)</th>
            <th>Дата</th>
          </tr>
        </thead>
        <tbody>
          {entries.map((entry) => (
            <tr key={entry.id}>
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