/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors:{
        "primary-color":"#009FFD",
        "secondary-color":"#B0E0E6"
      }
    },
  },
  plugins: [],
}