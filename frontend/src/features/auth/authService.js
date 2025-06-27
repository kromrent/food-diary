const API_URL = 'http://localhost:8080/api/auth/login';

const login = async (email, password) => {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ email, password })
  });

  if (!response.ok) {
    throw new Error('Ошибка авторизации');
  }

  const data = await response.json();
  return data.token; 
};

export default {
  login
};