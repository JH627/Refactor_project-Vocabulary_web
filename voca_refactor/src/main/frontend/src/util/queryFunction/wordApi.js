import { authInstance } from '../api';

export async function fetchWords({ searchCond, page }) {
  let wordUrl = '/words';

  if (page && searchCond) {
    wordUrl += '?searchCond=' + searchCond + '&page=' + page;
  } else if (page) {
    wordUrl += '?page=' + page;
  } else if (searchCond) {
    wordUrl += '?searchCond=' + searchCond;
  }

  try {
    const response = await authInstance.get(wordUrl);
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

export async function addWord(wordDetail) {
  let wordUrl = '/words';

  try {
    const response = await authInstance.post(wordUrl, wordDetail);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function editWord(wordDetail) {
  let wordUrl = '/words';

  try {
    const response = await authInstance.patch({ wordUrl, wordDetail });
    return response;
  } catch (error) {
    throw error;
  }
}
