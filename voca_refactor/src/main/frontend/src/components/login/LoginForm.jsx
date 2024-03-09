import React from 'react';

export default function Loginmain({ onSubmit, children }) {
  function handleSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData);

    onSubmit({ ...data });
  }

  return (
    <form id="login-form" onSubmit={handleSubmit}>
      <input type="text" name="username" placeholder="아이디" />
      <input type="password" name="password" placeholder="비밀번호" autoComplete="off" />
      <p>{children}</p>
    </form>
  );
}
