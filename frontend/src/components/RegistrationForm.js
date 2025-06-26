import React, { useState } from 'react';

const steps = [
  "Имя",
  "Пол и возраст",
  "Рост и вес",
  "Активность",
  "Пароль",
  "Подтверждение"
];

export default function RegistrationForm() {
  const [step, setStep] = useState(0);

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    gender: "MALE",
    age: "",
    height: "",
    weight: "",
    activityLevel: "MODERATE",
    password: "",
    confirm: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const nextStep = () => setStep((s) => Math.min(s + 1, steps.length - 1));
  const prevStep = () => setStep((s) => Math.max(s - 1, 0));
  
  const handleSubmit = async () => {
  if (formData.password !== formData.confirm) {
    alert("Пароли не совпадают");
    return;
  }

  const { confirm, ...payload } = formData;

  try {
    const res = await fetch("/api/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });

    if (res.ok) {
      const data = await res.json();
      console.log("Успешно:", data);
      alert("Регистрация успешна!");
      // Можно сделать переход или сброс формы
    } else {
      const error = await res.text();
      alert("Ошибка регистрации: " + error);
    }
  } catch (e) {
    console.error(e);
    alert("Ошибка соединения с сервером");
  }
};


  return (
    <div style={{ maxWidth: 400, margin: "0 auto", padding: 20 }}>
      <h2>{steps[step]}</h2>

      {step === 0 && (
        <>
            <label>Имя:</label>
            <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />
        </>
        )}
      {step === 1 && (
        <>
            <label>Пол:</label>
            <select
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            style={{ display: "block", width: "100%", marginBottom: 10 }}
    >
            <option value="MALE">Мужчина</option>
            <option value="FEMALE">Женщина</option>
    </select>

            <label>Возраст:</label>
            <input
            type="number"
            name="age"
            value={formData.age}
            onChange={handleChange}
            min="1"
            max="150"
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />
        </>
        )}
        {step === 2 && (
        <>
            <label>Рост (см):</label>
            <input
            type="number"
            name="height"
            value={formData.height}
            onChange={handleChange}
            min="50"
            max="250"
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />

            <label>Вес (кг):</label>
            <input
            type="number"
            name="weight"
            value={formData.weight}
            onChange={handleChange}
            min="20"
            max="500"
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />
        </>
        )}
        {step === 3 && (
        <>
            <label>Уровень физической активности:</label>
            <select
            name="activityLevel"
            value={formData.activityLevel}
            onChange={handleChange}
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            >
            <option value="LOW">Сидячий (1.2)</option>
            <option value="MODERATE">Умеренный (1.375)</option>
            <option value="ACTIVE">Активный (1.55)</option>
            <option value="VERY_ACTIVE">Очень активный (1.725)</option>
            </select>
        </>
        )}
        {step === 4 && (
        <>
            <label>Email:</label>
            <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />

            <label>Пароль:</label>
            <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />

            <label>Повторите пароль:</label>
            <input
            type="password"
            name="confirm"
            value={formData.confirm}
            onChange={handleChange}
            style={{ display: "block", width: "100%", marginBottom: 10 }}
            />
        </>
        )}
        {step === 5 && (
        <>
            <h3>Проверьте ваши данные:</h3>
            <ul style={{ marginBottom: 15 }}>
            <li><b>Имя:</b> {formData.name}</li>
            <li><b>Пол:</b> {formData.gender}</li>
            <li><b>Возраст:</b> {formData.age}</li>
            <li><b>Рост:</b> {formData.height} см</li>
            <li><b>Вес:</b> {formData.weight} кг</li>
            <li><b>Активность:</b> {formData.activityLevel}</li>
            <li><b>Email:</b> {formData.email}</li>
            </ul>

            <button onClick={handleSubmit}>Зарегистрироваться</button>
        </>
        )}


      <div style={{ display: "flex", justifyContent: "space-between", marginTop: 20 }}>
        {step > 0 && <button onClick={prevStep}>Назад</button>}
        {step < steps.length - 1 && <button onClick={nextStep}>Далее</button>}
      </div>
    </div>
  );
  
}