import React from "react";
import ProductList from "./ProductList";
import FoodEntryList from "./FoodEntryList"; 
import FoodEntryForm from "./FoodEntryForm";

function App() {
  return (
    <div className="App">
      <h1>Food Tracker</h1>
      <FoodEntryForm />
      <ProductList />
      <FoodEntryList /> 
    </div>
  );
}

export default App;