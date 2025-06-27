import React, { useState } from "react";
import RegistrationForm from './features/auth/RegistrationForm';
import LoginForm from './features/auth/LoginForm';
import FoodEntryForm from "./features/foodEntries/FoodEntryForm";
import FoodEntryList from './features/foodEntries/FoodEntryList';
import { FoodEntryProvider } from "./features/context/FoodEntryContext";

function App() {
  const [showEntries, setShowEntries] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [isLogin, setIsLogin] = useState(true);

  const isAuthenticated = !!token;

  const handleLoginSuccess = (newToken) => {
    localStorage.setItem("token", newToken);
    setToken(newToken);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setToken(null);
    setShowEntries(false); // Сбрасываем отображение записей при выходе
  };

  return (
    <FoodEntryProvider token={token}>
      <div className="App">
        <h1>Онлайн-дневник питания</h1>

        {isAuthenticated ? (
          <>
            <button onClick={handleLogout}>Выйти</button>
            
            <FoodEntryForm />

            <button onClick={() => setShowEntries(!showEntries)}>
              {showEntries ? "Скрыть записи" : "Показать записи"}
            </button>

            {showEntries && <FoodEntryList />}
          </>
        ) : (
          <>
            {isLogin ? (
              <LoginForm onLoginSuccess={handleLoginSuccess} />
            ) : (
              <RegistrationForm onRegisterSuccess={handleLoginSuccess} />
            )}

            <button onClick={() => setIsLogin(!isLogin)}>
              {isLogin ? "Нет аккаунта? Зарегистрироваться" : "Уже есть аккаунт? Войти"}
            </button>
          </>
        )}
      </div>
    </FoodEntryProvider>
  );
}

export default App;