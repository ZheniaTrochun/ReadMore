const TOKEN_KEY = 'secret-token-key'

export function storeToken(token) {
  localStorage.setItem(TOKEN_KEY, token)
}

export function getToken(token) {
  return localStorage.getItem(TOKEN_KEY)
}

export function isLoggedIn() {
  return localStorage.getItem(TOKEN_KEY) != null
}

export function deleteToken() {
  localStorage.removeItem(TOKEN_KEY)
}
