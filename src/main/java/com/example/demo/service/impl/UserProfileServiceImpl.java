@Override
public boolean existsByEmail(String email) {
    return repo.existsByEmail(email);
}

@Override
public UserProfile getByEmail(String email) {
    return repo.findByEmail(email).orElse(null);
}
