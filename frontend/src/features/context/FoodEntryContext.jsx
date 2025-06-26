// context/FoodEntryContext.jsx
import React, { createContext, useState, useEffect } from "react";
import axios from "axios";

export const FoodEntryContext = createContext();

export const FoodEntryProvider = ({ children, token }) => {
  const [entries, setEntries] = useState([]);

  const fetchEntries = () => {
    if (!token) return;
    axios.get("http://localhost:8080/api/food-entries/me", {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(response => setEntries(response.data))
      .catch(error => console.error("Ошибка при получении записей:", error));
  };

  useEffect(() => {
    fetchEntries();
  }, [token]);

  const addEntry = (entry) => {
    setEntries(prev => [...prev, entry]); 
    fetchEntries(); 
  };

  return (
    <FoodEntryContext.Provider value={{ entries, addEntry, fetchEntries }}>
      {children}
    </FoodEntryContext.Provider>
  );
};