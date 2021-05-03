# SpringSecurity

# Add Source Code to repo

1. Create a new repo in Git
2. Terminal > Change the current working directory to your local project.
3. Initialize the local directory as a Git repository.
      $ git init -b main
4. Add the files in your new local repository. This stages them for the first commit.
      $ git add .
     (Adds the files in the local repository and stages them for commit. To unstage a file, use 'git reset HEAD YOUR-FILE'.)
5. Commit the files that you've staged in your local repository.
    $ git commit -m "First commit"
    (Commits the tracked changes and prepares them to be pushed to a remote repository. To remove this commit and modify the file, use 'git reset --soft HEAD~1' and      commit and add the file again.)     
 6. Add remote repo url
    $ git remote add origin  https://github.com/girishgowd7290/SpringSecurity.git
            - Sets the new remote
    $ git remote -v
            - Verifies the new remote URL  
7. Push the changes in your local repository to GitHub.
    $ git branch -M main 
    $ git push -u origin main
            - Pushes the changes in your local repository up to the remote repository you specified as the origin 


References : https://docs.github.com/en/github/importing-your-projects-to-github/adding-an-existing-project-to-github-using-the-command-line
